http = require("http");

function sleep(milliSeconds, callback) {
    var startTime = new Date().getTime();
    while (new Date().getTime() < startTime + milliSeconds);
}

server = http.createServer(function (request, response) {
    console.log(request);
    console.log('a request');
    var data;
    if (request.url === '/sleep') {
        sleep(3000);
        data = '{"delay" : 3000}';
    } else if (request.url === '/') {
        data = '{"delay" : 0}';
    } else {
        data = '{"error" : "true"}';
        response.statusCode = 500;
    }
    response.end(data);
});

server.listen(9000);
console.log("running... on 9000");