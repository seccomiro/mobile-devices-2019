package br.edu.ifpr.stiehl.todolist.datasource

import br.edu.ifpr.stiehl.todolist.app.TodoApplication
import br.edu.ifpr.stiehl.todolist.entities.Task
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TaskRepository : TaskDataSource {
    val dataSource: TaskDataSource
        get() = if (TodoApplication.app.networkAvailable)
            TaskRemoteDataSource()
        else
            TaskLocalDataSource()

    val remoteDataSource = TaskRemoteDataSource()
    val localDataSource = TaskLocalDataSource()

    override fun getAll() =
        if (TodoApplication.app.networkAvailable) {
            remoteDataSource
                .getAll()
                .doOnSuccess { remoteTasks ->
                    localDataSource
                        .removeAll()
                        .doOnComplete {
                            localDataSource
                                .insertAll(remoteTasks)
                                .subscribe()
                        }
                        .subscribe()
                }
        } else {
            localDataSource
                .getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun insert(task: Task): Single<Long> {
        task.localStatus = Task.STATUS_INSERTED
        return localDataSource
            .insert(task)
            .doOnSuccess { localId ->
                task.localId = localId
                if (TodoApplication.app.networkAvailable)
                    remoteDataSource
                        .insert(task)
                        .doOnSuccess { remoteId ->
                            task.remoteId = remoteId
                            task.localStatus = null
                            localDataSource
                                .update(task)
                                .subscribe()
                        }
                        .subscribe()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun update(task: Task): Completable = dataSource
        .update(task)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun remove(task: Task) = dataSource
        .remove(task)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun insertAll(tasks: List<Task>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAll(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUnsynchronizedTasks(): Single<List<Task>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun synchronize() {
        val d = localDataSource
            .getUnsynchronizedTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { tasks ->
                tasks.forEach { task ->
                    remoteDataSource
                        .insert(task)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { remoteId ->
                            task.remoteId = remoteId
                            task.localStatus = null
                            localDataSource
                                .update(task)
                                .subscribe()
                        }
                }
            }
    }

}