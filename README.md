# backend.stecnology


<b>Project for learning purposes about Java, Spring Boot, RestAPI, JPA, HATEOS, Swagger e GIT.</b>

<h1>go up environment</h1>



    mvn install for generation .jar
    docker network create stec-network
    docker-compose -d --build
    
    
   
    

<h1>link for swagger</h1>

    link swagger: http://localhost:8080/swagger-ui.html#/


<h1>Pagination API</h1>

<h5>Use paramaters in routes for pagination</h5>

    page=0
 
 - <i>Attribute page is number of the page to be viewed </i>
 
    limit=5
    
- <i>Registration limit to be shown </i>

    direction=desc

- <i>Page ordering attribute</i>

<h1>Authetication with jwtToken</h1>

{
	
	"username": "string",
	"password": "string"
}

<h5>POST</h5>

1° Generate the token in route  /auth/signin:

    - Token example: { eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW11ZWwiLCJyb2xlcyI6W10sImlhdCI6MTYzMjE4MTIyMSwiZXhwIjoxNjMyMTgxMjIxfQ.Nc6taFlnpvbvYciXjYqrmtJZT9W_7b0Y8S2SiRGj2jU }

2° In the postman or insomnia, in the Header:

    - New header user a tag - Authorization
    - New value user - Bearer +token generate in the rota /auth/signin


<h1>API Routes File</h1>

<h5>GET</h5>

    - api/file/v1/downloadFile/{files}
     - download files, use requestParms as the file parameter


<h5>POST</h5>

    - api/file/v1/uploadMultipleFiles
    - upload multiple files, use requestParams with the file parameter



<h1>API Routes Person</h1> 

<h5>GET</h5>

    - api/person/v1
     - API findAll people recorded

<h5>GET</h5>

    - api/person/v1/{name}
     - API find people by name
    - exemplo request: @PathVariable("firstName");


<h5>GET</h5>

    - api/person/v1/{id}
     - API find people by id
     - exemplo request: @PathVariable("id");
 

<h5>POST</h5>

    - api/person/v1
    - Create a new person
 
request:

{
	
	"firstName": "string",
	"gender": "string",
	"lastName": "string",
	"enabled": "bollean"
}

response:


{
	
	"firstName": "string",
	"gender": "string",
	"lastName": "string",
	"enabled": "bollean",
	"_links": {
		"self": {
		  "href": "http://localhost:8080/api/person/v1/1"
		}
	},
	"id": 1
}



<h5>PUT</h5>

    - /api/person/v1
    - Update a person
    - use requestBody, passing the {id}
 
    request:
 {

	"id": long,
	"firstName": "string",
	"gender": "string",
	"lastName": "string",
	"enabled": "bollean"
}

    response: 

    return people updated

<h5>DELETE</h5>

    - api/person/v1/{id}
     - Delete people by id

<h5>PATCH</h5>

    - api/person/v1/{id}
     - Updated status enabled the person for disable = enable: false
 
    response: 
 
{
	
	"firstName": "string",
	"gender": "string",
	"lastName": "string",
	"enabled": "false",
	"_links": {
		"self": {
		  "href": "http://localhost:8080/api/person/v1/1"
		}
	},
	"id": 1
}



-------------------------------------------------------------------------------------

<h1>API Routes Book</h1> 

<h5>GET</h5>

    - api/book/v1
     - API findAll book recorded

<h5>GET</h5>

    - api/book/v1/{id}
     - API find book by id
     - exemplo request: @PathVariable("id");
 

<h5>POST</h5>

     - api/book/v1
      - Create a new book
 
request:

{
	
	"author": "string",
	"launch_date": Date,
	"price": Double,
	"title": "bollean"
}

response:


{
	
	"author": "string",
	"launch_date": Date,
	"price": Double,
	"title": "string",
	"_links": {
		"self": {
		  "href": "http://localhost:8080/api/book/v1/1"
		}
	},
	"id": long
}



<h5>PUT</h5>

    - /api/book/v1
    - Update a book
    - use requestBody, passing the {id}
 
 request:
 {

	"id": long,
	"firstName": "string",
	"gender": "string",
	"lastName": "string",
	"enabled": "bollean"
}

response: 

    return book updated

<h5>DELETE</h5>

    - api/book/v1/{id}
    - Delete book by id


 
