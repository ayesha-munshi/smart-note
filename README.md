# Welcome to SmartNote Application

## Requirements

The project requires Java 11 and makes use of Maven

### Run the unit tests

  ```console
  $ mvn test
  ```

### Run the application using maven

Run the application which will be listening on port `8080`:

```console
$ mvn spring-boot:run
```
### Run the application inside container

Building an image :

```console
$ docker build -t bootdocker:1 .
```

Running the container :

```console
$ docker run -d --name bootdocker -p 8080:8080 bootdocker:1
```

## API

Below is a list of API endpoints with their respective input and output.

### Create Notebook

Endpoint

```text
POST api/v1/notebooks
```

Example of body

```json
{
    "notebookDescription": "test notebook",
    "notes": [
        {
        "title": "test note1",
        "body": "TODO list 1", 
        "tag": [
            "tag1", "tag2"
        ]
        },
        {
        "title": "test note2",
        "body": "TODO list 2", 
        "tag": [
            "tag5", "tag2"
        ]
        }
    ]
}
```
Example Output

```json
{
    "notebookId": 2,
    "notebookDescription": "test notebook",
    "notes": [
        {
            "id": 3,
            "title": "test note1",
            "body": "TODO list 1",
            "createdTime": "2022-05-17T01:52:58.0021902",
            "modifiedTime": "2022-05-17T01:52:58.0021902",
            "tag": [
                "tag1",
                "tag2"
            ]
        },
        {
            "id": 4,
            "title": "test note2",
            "body": "TODO list 2",
            "createdTime": "2022-05-17T01:52:58.0037265",
            "modifiedTime": "2022-05-17T01:52:58.0037265",
            "tag": [
                "tag5",
                "tag2"
            ]
        }
    ]
}
```
### Read Notebook

Endpoint

```text
GET api/v1/notebooks/<notebookId>
```

Parameters

| Parameter    | Description        |
|--------------|--------------------|
| `notebookId` | Id of the notebook |

Example of output

```json
{
    "notebookId": 2,
    "notebookDescription": "test notebook",
    "notes": [
        {
            "id": 3,
            "title": "test note1",
            "body": "TODO list 1",
            "createdTime": "2022-05-17T01:15:02.110205",
            "modifiedTime": "2022-05-17T01:15:02.110205",
            "tag": [
                "tag1",
                "tag2"
            ]
        },
        {
            "id": 4,
            "title": "test note2",
            "body": "TODO list 2",
            "createdTime": "2022-05-17T01:15:02.11186",
            "modifiedTime": "2022-05-17T01:15:02.11186",
            "tag": [
                "tag5",
                "tag2"
            ]
        },
        {
            "id": 5,
            "title": "test note1",
            "body": "TODO list 1",
            "createdTime": "2022-05-17T01:15:05.851773",
            "modifiedTime": "2022-05-17T01:15:05.851773",
            "tag": [
                "tag5",
                "tag7"
            ]
        }
    ]
}
```
### Update Notebook

Endpoint

```text
PUT api/v1/notebooks/<notebookId>?[?noteDescription=<noteDescription>]
```

Parameters

| Parameter         | Description             |
|-------------------|-------------------------|
| `notebookId`      | Id of the notebook      |
| `noteDescription` | Description of notebook |


### Delete Notebook

Endpoint

```text
DELETE api/v1/notebooks/<notebookId>
```

Parameters

| Parameter    | Description        |
|--------------|--------------------|
| `notebookId` | Id of the notebook |

### Read Notebook by tag

Endpoint

```text
DELETE api/v1/notebooks/<notebookId>?[?tag=<tag>]
```

Parameters

| Parameter    | Description                      |
|--------------|----------------------------------|
| `notebookId` | Id of the notebook               |
| `tag`        | tag which is applied to the note |

Example of output

```json
{
    "notebookId": 2,
    "notebookDescription": "test notebook",
    "notes": [
        {
            "id": 5,
            "title": "test note1",
            "body": "TODO list 1",
            "createdTime": "2022-05-17T01:15:05.851773",
            "modifiedTime": "2022-05-17T01:15:05.851773",
            "tag": [
                "tag5",
                "tag7"
            ]
        }
    ]
}
```
### Create Note

Endpoint

```text
POST api/v1/notebooks/<notebookId>/notes
```

Example of body

```json
{
  "title": "test note1",
  "body": "TODO list 1",
  "tag": [
    "tag5", "tag7"
  ]
}
```
Example Output

```json
{
    "id": 5,
    "title": "test note1",
    "body": "TODO list 1",
    "createdTime": "2022-05-17T01:15:05.8517731",
    "modifiedTime": "2022-05-17T01:15:05.8517731",
    "tag": [
        "tag5",
        "tag7"
    ],
    "notebook": {
        "notebookId": 2,
        "notebookDescription": "test notebook"
    }
}
```

### Read Note

Endpoint

```text
GET api/v1/notes/<noteId>
```

Parameters

| Parameter | Description    |
|-----------|----------------|
| `noteId`  | Id of the note |

Example of output

```json
{
  "id": 3,
  "title": "test note1",
  "body": "TODO list 1",
  "createdTime": "2022-05-17T01:15:02.110205",
  "modifiedTime": "2022-05-17T01:15:02.110205",
  "tag": [
    "tag1",
    "tag2"
  ],
  "notebook": {
    "notebookId": 2,
    "notebookDescription": "test notebook"
  }
}
```
### Update Note

Endpoint

```text
PUT api/v1/notes/<noteId>
```

Parameters

| Parameter     | Description         |
|---------------|---------------------|
| `noteId`      | Id of the note      |

Example of body

```json
{
  "title": "test note1",
  "body": "TODO list 1",
  "tag": [
    "tag5", "tag7"
  ]
}
```

### Delete Note

Endpoint

```text
DELETE api/v1/notebooks/<notebookId>/notes/<noteId>
```

Parameters

| Parameter     | Description        |
|---------------|--------------------|
| `notebookId`  | Id of the notebook |
| `noteId`      | Id of the note     |  

### Read Note by tag

Endpoint

```text
DELETE api/v1/notes?[?tag=<tag>]
```

Parameters

| Parameter | Description                      |
|-----------|----------------------------------|
| `tag`     | tag which is applied to the note |

Example of output

```json
[
  {
    "id": 1,
    "title": "test note1",
    "body": "TODO list 1",
    "createdTime": "2022-05-17T01:14:55.520671",
    "modifiedTime": "2022-05-17T01:14:55.520671",
    "tag": [
      "tag1",
      "tag2"
    ],
    "notebook": {
      "notebookId": 1,
      "notebookDescription": "test notebook"
    }
  },
  {
    "id": 2,
    "title": "test note2",
    "body": "TODO list 2",
    "createdTime": "2022-05-17T01:14:55.524664",
    "modifiedTime": "2022-05-17T01:14:55.524664",
    "tag": [
      "tag5",
      "tag2"
    ],
    "notebook": {
      "notebookId": 1,
      "notebookDescription": "test notebook"
    }
  },
  {
    "id": 3,
    "title": "test note1",
    "body": "TODO list 1",
    "createdTime": "2022-05-17T01:15:02.110205",
    "modifiedTime": "2022-05-17T01:15:02.110205",
    "tag": [
      "tag1",
      "tag2"
    ],
    "notebook": {
      "notebookId": 2,
      "notebookDescription": "test notebook"
    }
  },
  {
    "id": 4,
    "title": "test note2",
    "body": "TODO list 2",
    "createdTime": "2022-05-17T01:15:02.11186",
    "modifiedTime": "2022-05-17T01:15:02.11186",
    "tag": [
      "tag5",
      "tag2"
    ],
    "notebook": {
      "notebookId": 2,
      "notebookDescription": "test notebook"
    }
  }
]
```








