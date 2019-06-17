function WSSHClient() {
};

var url = "localhost:8081/demo_docker/ws/websocket";
WSSHClient.prototype._generateEndpoint = function () {
    if (window.location.protocol == 'https:') {
        var protocol = 'wss://';
    } else {
        var protocol = 'ws://';
    }
    var endpoint = protocol + url;
    return endpoint;
};

WSSHClient.prototype.connect = function (options) {
    var endpoint = this._generateEndpoint();

    if (window.WebSocket) {
        this._connection = new WebSocket(endpoint);
    }
    else if (window.MozWebSocket) {
        this._connection = MozWebSocket(endpoint);
    }
    else {
        options.onError('WebSocket Not Supported');
        return;
    }

    this._connection.onopen = function () {
        options.onConnect();
    };

    this._connection.onmessage = function (evt) {
        var data = evt.data.toString()
        options.onData(data);
    };


    this._connection.onclose = function (evt) {
        options.onClose();
    };
};

WSSHClient.prototype.send = function (data) {
    this._connection.send(JSON.stringify(data));
};

WSSHClient.prototype.sendInitData = function (options) {
    // var data = {
    //     hostname: options.host,
    //     port: options.port,
    //     username: options.username,
    //     ispwd: options.ispwd,
    //     secret: options.secret
    // };
    var data = {
        hostname: "139.198.176.43",
        port: 3033,
        username: "jenkins",
        secret: "3er4#ER$"
    };
    this._connection.send(JSON.stringify({"tp": "init", "data": data}))
}
WSSHClient.prototype.sendClientData = function (data) {
    this._connection.send(JSON.stringify({"tp": "client", "message": data}))
}


var client = new WSSHClient();
