var StockMarket = (function(){

    var instance = axios.create({
        baseURL: 'https://arsw-lab-7.herokuapp.com/stockmarket'
        // baseURL: 'http://localhost:8080/stockmarket',
        // headers: {'Access-Control-Allow-Origin': 'http://localhost:8080'}
    });

    var getInfo = function () {
        var func = document.getElementsByClassName("active")[0].id;
        var symbol = document.getElementById("inp").value;
        if(func === "intraday") {
            instance.get('/' + func + '/' + symbol + '/' + document.getElementById("select").value)
                .then(function (response) {
                    // console.log(response.data);
                    _generate_table(response.data);
                })
                .catch(function (error) {
                    var div = document.getElementById("info");
                    var p = document.createElement("p");
                    var pt = document.createTextNode("Error");
                    p.appendChild(pt);
                    div.appendChild(p);
                    console.log(error);
                });
        } else {
            instance.get('/' + func + '/' + symbol)
                .then(function (response) {
                    // console.log(response.data);
                    _generate_table(response.data);
                })
                .catch(function (error) {
                    var div = document.getElementById("info");
                    var p = document.createElement("p");
                    var pt = document.createTextNode("Error");
                    p.appendChild(pt);
                    div.appendChild(p);
                    console.log(error);
                });
        }

    };

    var _generate_table = function (data) {
        // get the reference for the div
        var div = document.getElementById("info");
        if(document.getElementById("table") !== null) {
            div.removeChild(document.getElementById("table"));
        }
        // console.log(document.getElementById("table"));
        var keys = Object.keys(data[Object.keys(data)[1]]);

        // creates a <table> element and a <tbody> element
        var tbl = document.createElement("table");
        tbl.setAttribute("id","table");
        var tblBody = document.createElement("tbody");

        // create the title cells
        var r = document.createElement("tr");
        var c1 = document.createElement("td");
        var c1t = document.createTextNode("Time");
        c1.appendChild(c1t);
        var c2 = document.createElement("td");
        var c2t = document.createTextNode("Open");
        c2.appendChild(c2t);
        var c3 = document.createElement("td");
        var c3t = document.createTextNode("High");
        c3.appendChild(c3t);
        var c4 = document.createElement("td");
        var c4t = document.createTextNode("Low");
        c4.appendChild(c4t);
        var c5 = document.createElement("td");
        var c5t = document.createTextNode("Close");
        c5.appendChild(c5t);
        var c6 = document.createElement("td");
        var c6t = document.createTextNode("Volume");
        c6.appendChild(c6t);
        r.appendChild(c1);
        r.appendChild(c2);
        r.appendChild(c3);
        r.appendChild(c4);
        r.appendChild(c5);
        r.appendChild(c6);
        tblBody.appendChild(r);

        // creating all cells
        for (var i = 0; i < keys.length; i++) {
            // creates a table row
            var row = document.createElement("tr");

            var cell1 = document.createElement("td");
            var cellText1 = document.createTextNode(keys[i]);
            cell1.appendChild(cellText1);
            row.appendChild(cell1);
            var cells = Object.keys(data[Object.keys(data)[1]][keys[i]]);

            for (var j = 0; j < cells.length; j++) {
                // Create a <td> element and a text node, make the text
                // node the contents of the <td>, and put the <td> at
                // the end of the table row
                var cell = document.createElement("td");
                var cellText = document.createTextNode(data[Object.keys(data)[1]][keys[i]][cells[j]]);
                cell.appendChild(cellText);
                row.appendChild(cell);
            }

            // add the row to the end of the table body
            tblBody.appendChild(row);
        }

        // put the <tbody> in the <table>
        tbl.appendChild(tblBody);
        // appends <table> into <body>
        div.appendChild(tbl);
        // sets the border attribute of tbl to 2;
        tbl.setAttribute("border", "2");
    };

    return {
        getInfo:getInfo
    };

})();