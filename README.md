# backend.stecnology


<b>Project for learning purposes about Java, Spring Boot, RestAPI, JPA, HATEOS, Swagger e GIT.</b>

<h1>go up environment</h1>



    mvn install for generation .jar
    docker network create stec-network
    docker push dockersam123/stecnology-api:latest
    docker-compose -d --build
    
    
   
<b>AWS - Amazon web service - create image in repository aws</b> 
   
    1°- Create database aws -> create database RDS mysql, get value in endpoint the database in local
       - Name databse -> awsdbstecnology

    2°- Create user IAM -> Create user -> Access Key: programatic key -> Attach existing directly
       - AmazonEC2ContainerRegistryPowerUser
    3°- Create Cluster ECS, Elastic Container Service -> Cluster -> create Cluster
      - EC2 Linux + Networking
      - name: AWSstecnologyCluster
      - Create Key_pair in EC2Console
    4°- Create repository ECR, Elastic Container Registry
       - Get Start -> name repository will be the name of the image docker -> dockersam123/stecnology-api
       - image -> 039282644276.dkr.ecr.us-east-2.amazonaws.com/dockersam123/stecnology-api
       - Using CLI steps to upload the image to the repository AWS instal AWS CLI
       - in terminal use aws configure
       - Use token the crendial aws create in process
       - Default region name [us-east-2]: us-east-2 and default json
       - docker-compose up -b --build
     
      1° - aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 039282644276.dkr.ecr.us-east-2.amazonaws.com
      2° - docker build -t dockersam123/stecnology-api ., do not necessary
      3° - docker tag dockersam123/stecnology-api:latest 039282644276.dkr.ecr.us-east-2.amazonaws.com/dockersam123/stecnology-api:latest
      4° - docker push 039282644276.dkr.ecr.us-east-2.amazonaws.com/dockersam123/stecnology-api:latest

    5°- Create a Task definitions -> Create a new task definition
       1° - Options EC2
       2° - name: AWSstecnologyTask
       3° - add container
         1° - name container: AWSstecnologyTask
         2° - paste image repository image in box image:latest
         3° - port mapping 8080

    6°- Create Service -> in task definitions -> actions -> create Service
      1°- Options EC2
        - ec2-3-129-88-120.us-east-2.compute.amazonaws.com -> DNS IPv4 público
    7°- Acess terminal with key
      1°- ssh -i "MY_KEY_PAIR.pem" ec2-user@ec2-3-129-88-120.us-east-2.compute.amazonaws.com
        - Access via ssh terminal in the folder where KEY_PAIR
    
    
    

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


 
