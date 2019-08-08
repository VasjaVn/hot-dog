IP_ADDR='192.168.1.8'
PORT='80'
URL='http://' + IP_ADDR + ':' + PORT;

String.prototype.isEmpty = function() {
    return (this.length === 0 || !this.trim());
};

if (window.XMLHttpRequest) {
    xmlhttp = new XMLHttpRequest();
} else {
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
}

xmlhttp.open('GET', URL + '/v1/hotdogs', true);
xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var response = JSON.parse(this.responseText);
        console.log(response);
        if (response.status === 'SUCCESS') {
            var options = {
                valueNames: [ 'id', 'name' ],
                item: '<li onclick="clickHotDogItem(this);"><p class="id"></p><p class="name"></p></li>'
            };
            hotdogList = new List('hotdog-list', options, response.result);
        } else {
            setErrorMessage('Can not read list of hot dogs');
        }
    }
};
xmlhttp.send();

function addHotDog() {
    var hotDogName = document.getElementById('hotDogName').value;
    if (hotDogName.isEmpty()) {
        setErrorMessage('Enter name of a hot dog for creating');
    } else {
        console.log(name);
        xmlhttp.open('POST', URL + '/v1/hotdog', true);
        xmlhttp.setRequestHeader('Content-type', 'application/json');
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var response = JSON.parse(this.responseText);
                console.log(response);
                if (response.status === 'SUCCESS') {
                    hotdogList.add({
                        id: response.result.id,
                        name: response.result.name
                    });
                    clear();
                } else {
                    setErrorMessage(response.msg);
                }
            }
        };
        xmlhttp.send(JSON.stringify({'id':0, 'name':hotDogName}));
    }
}

function removeHotDog() {
    var hotDogId = document.getElementById('hotDogId').value;
    if (!hotDogId.isEmpty()) {
        xmlhttp.open('DELETE', URL + '/v1/hotdog/' + hotDogId, true);
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var response = JSON.parse(this.responseText);
                console.log(response.status);
                if (response.status === 'SUCCESS') {
                    hotdogList.remove('id', hotDogId);
                    clear();
                } else {
                    setErrorMessage(response.msg);
                }
            }
        };
        xmlhttp.send();
    } else {
        setErrorMessage('Select a hot dog for removing');
    }
}

function updateHotDog() {
    var hotDogId = document.getElementById('hotDogId').value;
    var hotDogName = document.getElementById('hotDogName').value;
    if (hotDogId.isEmpty()) {
        setErrorMessage('Select a hot dog and change name for updating');
    } else if (hotDogName.isEmpty()) {
        setErrorMessage('Name of a hot dog can not be empty for updating');
    } else {
        xmlhttp.open('PUT', URL + '/v1/hotdog', true);
        xmlhttp.setRequestHeader('Content-type', 'application/json');
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var response = JSON.parse(this.responseText);
                console.log(response.status);
                if (response.status === 'SUCCESS') {
                    clear();
                    hotdogList.remove('id', hotDogId);
                    hotdogList.add({ id: response.result.id, name: response.result.name });
                } else {
                    setErrorMessage(response.msg);
                }
            }
        };
        xmlhttp.send(JSON.stringify({'id':hotDogId, 'name':hotDogName}));
    }
}

function changeSearch() {
    var value = document.getElementById('search').value;
    if (isNaN(Number(value))) {
        var hotdogs = hotdogList.get('name', value);
        if (hotdogs.length != 0) {
            document.getElementById('hotDogId').value = hotdogs[0]._values.id;
            document.getElementById('hotDogName').value = hotdogs[0]._values.name;
        }
    } else {
        var hotdogs = hotdogList.get('id', value);
        if (hotdogs.length != 0) {
            document.getElementById('hotDogId').value = hotdogs[0]._values.id;
            document.getElementById('hotDogName').value = hotdogs[0]._values.name;
        }
    }
}

function clickHotDogItem(obj) {
    clearErrorMessage();
    document.getElementById('hotDogId').value = obj.getElementsByTagName('p')[0].innerHTML;
    document.getElementById('hotDogName').value = obj.getElementsByTagName('p')[1].innerHTML;
}

function clear() {
    document.getElementById('hotDogId').value = '';
    document.getElementById('hotDogName').value = '';
    clearErrorMessage();
}

function setErrorMessage(errorMsg) {
    document.getElementById('error').innerText = errorMsg;
}

function clearErrorMessage() {
    document.getElementById('error').innerText = '';
}