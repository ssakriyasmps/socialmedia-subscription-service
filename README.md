# socialmedia-subscription-service

## Adding subscriptions
POST /subscriptions HTTP/1.1

Host: localhost:9003

Content-Type: application/json

`{
"userId": 1,
"socialMedia": [
"facebook",
"snapchat"
]
}`

## Getting subscriptions for specific user

GET /subscriptions/3 HTTP/1.1

Host: localhost:9003

Content-Type: application/json











