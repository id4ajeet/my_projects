# Phone Record MS

### Database Structure
```
Table : call_data(id, uuid, domain_name, caller_name, caller_number, destination_number, direction, call_start, 
                  ring_start, answer_start, call_end, duration, recording, click_to_call, click_to_call_data, action)
Store Call records from sync and webook


Table : phone_book(number, name, email)
Store phone book contacts

Table : sync_data(id, sync_date)
stores sync date

View: cdr_report(uuid, successful_calls_minutes, answered_call_count, unmatched_call_count, total_call)
Used to generate the cdr report
```

### How to test

Step 1: Start the mock service

Step 2: run mock get_cdr mutiple times `curl -X GET "https://localhost:3030/get_cdr"`

Step 3: Start the DB using `docker-compose up db`

Step 4: Start the MS using `docker-compose up ms`

Step 5: Call phone-book api to store the valid contacts

```shell script
curl --location --request PUT 'http://localhost:8080/phonebook/all' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "number": 35799123456,
        "name": "John Smith",
        "email": "j.smith@impactechs.com"
    },
    {
        "number": 35799123457,
        "name": "Lennox Jeffery",
        "email": "l.jeffery@impactechs.com"
    },
    {
        "number": 35799123458,
        "name": "Tyrell Larsen",
        "email": "t.larsen@impactechs.com"
    },
    {
        "number": 35799123459,
        "name": "Sadia Bryan",
        "email": "s.bryan@impactechs.com"
    }
]'
```

Step 6: Call the Webhook to store cdr `curl --location --request GET 'http://localhost:3030/trigger_webhooks'`
        Note: `webhook_url` under settings.js is set to `http://localhost:8080/webhook` 

Step 7: Call the sync api to consume the data from mocked get_cdr `curl --location --request GET 'http://localhost:8080/sync'`

step 8: Call the Report API to generate the report `curl --location --request GET 'http://localhost:8080/report'`

Step 9: For Individual User report call with uuid `curl --location --request GET 'http://localhost:8080/report/5e9d0238-8e46-4a4e-a804-b18638927e01'`


### Exposed APIs 

#### Phone Book Directory APIs

*Store single contact*
```shell script
curl --location --request POST 'http://localhost:8080/phonebook' \
--header 'Content-Type: application/json' \
--data-raw '{
    "number": "9090343432",
    "name": "Jay",
    "email": "jay@abc.in"
}'
```
*Store multiple contact*
```shell script
curl --location --request PUT 'http://localhost:8080/phonebook/all' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "number": 35799123456,
        "name": "John Smith",
        "email": "j.smith@impactechs.com"
    },
    {
        "number": 35799123457,
        "name": "Lennox Jeffery",
        "email": "l.jeffery@impactechs.com"
    },
    {
        "number": 35799123458,
        "name": "Tyrell Larsen",
        "email": "t.larsen@impactechs.com"
    },
    {
        "number": 35799123459,
        "name": "Sadia Bryan",
        "email": "s.bryan@impactechs.com"
    }
]'
```
*fetch single contact*
```shell script
curl --location --request GET 'http://localhost:8080/phonebook/9090343432' \
--header 'Accept: application/json'
```
*fetch all contact*
```shell script
curl --location --request GET 'http://localhost:8080/phonebook' \
--header 'Accept: application/json'
```
*Delete Contact*
```shell script
curl --location --request DELETE 'http://localhost:8080/phonebook/9090343432' \
--header 'Accept: application/json'
```

#### Sync API
This API calls `curl -X GET "https://localhost:3030/get_cdr` to get the data, its url can be changed for integration using property
`pbx.server.baseUrl=http://localhost:3030/get_cdr`

```shell script
curl --location --request GET 'http://localhost:8080/sync'
```

#### Webhook API
```shell script
curl --location --request POST 'http://localhost:8080/webhook' \
--header 'Content-Type: application/json' \
--data-raw '{
    "uuid": "970f1baa-1ce8-4bbc-b0fb-bbc6647eb672",
    "domain_name": "mockpbx.impactpbx.com",
    "caller_name": "",
    "caller_number": 35725262136,
    "destination_number": 35799123458,
    "direction": "outbound",
    "call_start": "2020-09-13T20:35:11.000Z",
    "ring_start": "2020-09-13T20:35:14.000Z",
    "answer_start": "2020-09-13T20:35:20.000Z",
    "call_end": "2020-09-13T20:37:14.000Z",
    "duration": 123,
    "recording": "1d9ca29a-b586-4ad4-9405-417504981192",
    "click_to_call": false,
    "click_to_call_data": "",
    "action": "HANGUP"
}'
```
#### Report API
*Get full report *
```shell script
curl --location --request GET 'http://localhost:8080/report'
```

*Get report for uuid*
```shell script
curl --location --request GET 'http://localhost:8080/report/5e9d0238-8e46-4a4e-a804-b18638927e01'
```

#### CDR API
*Get all CDR*
```shell script
curl --location --request GET 'http://localhost:8080/cdr'
```

*Get CDR for uuid*
```shell script
curl --location --request GET 'http://localhost:8080/cdr/015a7920-3ca3-47dd-b400-bb491323f072'
```

*Store CDR*
```shell script
curl --location --request POST 'http://localhost:8080/cdr' \
--header 'Content-Type: application/json' \
--data-raw '{
        "uuid": "015a7920-3ca3-47dd-b400-bb491323f072",
        "domain_name": "mockpbx.impactpbx.com",
        "caller_name": "",
        "caller_number": 35725262136,
        "destination_number": 35799123458,
        "direction": "outbound",
        "call_start": "2020-09-15T09:49:21.000Z",
        "ring_start": null,
        "answer_start": null,
        "call_end": null,
        "duration": 0,
        "recording": null,
        "click_to_call": false,
        "click_to_call_data": "",
        "action": "ORIGINATE"
    }'
```
or 
```shell script
curl --location --request PUT 'http://localhost:8080/cdr' \
--header 'Content-Type: application/json' \
--data-raw '{
        "uuid": "015a7920-3ca3-47dd-b400-bb491323f072",
        "domain_name": "mockpbx.impactpbx.com",
        "caller_name": "",
        "caller_number": 35725262136,
        "destination_number": 35799123458,
        "direction": "outbound",
        "call_start": "2020-09-15T09:49:21.000Z",
        "ring_start": null,
        "answer_start": null,
        "call_end": null,
        "duration": 0,
        "recording": null,
        "click_to_call": false,
        "click_to_call_data": "",
        "action": "ORIGINATE"
    }'
```
*Delete CDR by uuid*
```shell script
curl --location --request DELETE 'http://localhost:8080/cdr/015a7920-3ca3-47dd-b400-bb491323f072'
```
