<?php
  $tasks = [
    0 => [
      "id" => 1,
      "title" => "First Task",
      "description" => "First Task Description",
      "done" => true
    ],
    1 => [
      "id" => 2,
      "title" => "Second Task",
      "description" => "Second Task Description",
      "done" => false
    ],
    2 => [
      "id" => 3,
      "title" => "Third Task",
      "description" => "Third Task Description",
      "done" => false
    ],
    3 => [
      "id" => 4,
      "title" => "Fourth Task",
      "description" => "Fourth Task Description",
      "done" => true
    ],
  ];

  echo json_encode($tasks);
?>