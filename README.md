# Backend

BaseURL: https://waterplantsbw.herokuapp.com

Auth:
POST:"/register", (username,password,phoneNumber) 

POST:"/login" ,(username,password)

Update User
PATCH: "/users/user/:id",(phoneNumber (&&,||) password)

Plants:
GET: "/plants" -returns all plants

GET: "/plants/plant/:id" - get by id

POST: "/plants/plant" - ("h2oFrequency","nickname","species")

PUT: "/plants/plant/:id" - update plant by id ("h2oFrequency","nickname","species")

PATCH: "/plants/plant/:id" - update fields(ex: nickname)

DELETE: "/plants/plant/:id" -delete by id

