var StockMarket = (function(){

    var instance = axios.create({
        baseURL: 'https://arsw-lab-7.herokuapp.com/stockmarket'
    });

    var getInfo = function () {
        var func = document.getElementsByClassName("active")[0].id;
        var symbol = document.getElementById("inp").value;
        if(func === "intraday") {
            instance.get('/' + func + '/' + symbol + '/' + document.getElementById("select").value)
                .then(function (response) {
                    var div = document.getElementById("info");
                    var p = document.createElement("p");
                    var pText = document.createTextNode(response.data);
                    p.appendChild(pText);
                    div.appendChild(p);
                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
                });
        } else {
            instance.get('/' + func + '/' + symbol)
                .then(function (response) {
                    var div = document.getElementById("info");
                    var p = document.createElement("p");
                    var pText = document.createTextNode(response.data);
                    p.appendChild(pText);
                    div.appendChild(p);
                })
                .catch(function (error) {
                    // handle error
                    console.log(error);
                });
        }

    };

    return {
        getInfo:getInfo
    };

})();