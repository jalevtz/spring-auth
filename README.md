# Example tu use authentication and authorization
## end-point to register a new user by default the users are created with USER role
### /auth/sign-up
body:
{
"name":"User Fake",
"lastName": "Fake Fake",
"email": "fake@gmail.com",
"password": "fake123"
}

## end-point to login a user
### /auth/sign-in
{
"email": "fake@gmail.com",
"password": "fake123"
}

### GET /sale end-point protected, get example data sales.
### GET /person end-point protected and Only for user has ADMIN rol,
### User example with admin grants mail:j.vlt.mtz@gmail.com, Password: password123


