import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description("Should return a student")

    // Defining how the request will look like
    request {
        method GET()
        url "/students/1"
    }
    // Defining how the response will look like
    response {
        status OK()
        headers{
            contentType applicationJson()
        }
        // We can define the response as JSON literal || as an object which gets converted to JSON
        body(
                id:1,
                name: "Mark",
                grade:10
        )
    }
}