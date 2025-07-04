##

Example tu use authentication and authorization
end-point to register a new user
/auth/sign-up
body:
{
"name":"User Fake",
"lastName": "Fake Fake",
"email": "fake@gmail.com",
"password": "fake123"
}

end-point to login a user
/auth/sign-in
{
"email": "fake@gmail.com",
"password": "fake123"
}
end-point protected, get example data sales.
GET /sale

end-point protected and Onlye for user has ADMIN rol, get example data sales.
GET /person


