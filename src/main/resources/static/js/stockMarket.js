var StockMarket = (function(){

    var instance = axios.create({
        baseURL: 'https://arsw-lab-7.herokuapp.com/stockmarket'
    });

    var getInfo = function () {
        var func = document.getElementsByClassName("active")[0].id;
        var symbol = document.getElementById("inp").value;
        instance.get('/' + func + '/' + symbol)
            .then(function (response) {
                // handle success
                console.log(response);
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            });
    };

    return {
        getInfo:getInfo
    };

})();