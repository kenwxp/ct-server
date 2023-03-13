# Promotion Requst

## 1. query rules

```sh
http POST ':9090/bing-api/mp/customer/draw/rules'  \
userId=e4011707-a691-11ed-8957-0242ac110003
```

## 2. query draw state

```sh
http POST ':9090/bing-api/mp/customer/draw/query'  \
userId=e4011707-a691-11ed-8957-0242ac110003
```

## 3. draw win

```sh
http POST ':9090/bing-api/mp/customer/draw/exchange' \
userId=e4011707-a691-11ed-8957-0242ac110003 \
ruleId=537c55f6-bf0d-11ed-8957-0242ac110004
```
