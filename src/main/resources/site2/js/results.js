(function ($) {
    var result = '<tr>' +
        '<td>{number}</td>' +
        '<td>{product}</td>' +
        '<td>{category}</td>' +
        '<td>${price}</td>' +
        '<td>{status}</td>' +
        '</tr>';

    var data = '[{"product":"Product 1","category":"Category 1","price":"144","status":"Status 1"},{"product":"Product 2","category":"Category 2","price":"113","status":"Status 2"},{"product":"Product 3","category":"Category 3","price":"232","status":"Status 3"},{"product":"Product 4","category":"Category 4","price":"332","status":"Status 4"},{"product":"Product 5","category":"Category 5","price":"102","status":"Status 5"},{"product":"Product 6","category":"Category 6","price":"203","status":"Status 6"},{"product":"Product 7","category":"Category 7","price":"482","status":"Status 7"},{"product":"Product 8","category":"Category 8","price":"162","status":"Status 8"},{"product":"Product 9","category":"Category 9","price":"218","status":"Status 9"},{"product":"Product 10","category":"Category 10","price":"200","status":"Status 10"}]';

    $(document).ready(function () {
        disablePage();
        let reportData = loadProducts();

        buildReportList(reportData);
    })

    $('#sort').on('change', function(){
        let sortBy = $(this). children("option:selected"). val();
        let reportData = loadProducts(true, sortBy);
        buildReportList(reportData);
    })

    let startLoader = function(){

        $('.page-loader').preloader();
    }

    let stopLoader = function(){
        let timer = (Math.floor(Math.random() * (5 - 2 + 1) + 2)) * 1000;
        setTimeout(function(){ $('.page-loader').preloader('remove'); }, timer);
        setTimeout(function(){ enablePage(); }, timer);
    }

    let enablePage = function(){
        $('#sort').show();
        $('#results').show();
    }

    let disablePage = function(){
        $('#sort').hide();
        $('#results').hide();
    }

    let loadProducts = function(sorted = false, order = "desc"){
        disablePage();
        startLoader();

        let products = JSON.parse(data);

        if(sorted){
            if(order === 'desc'){
                products.sort((a, b) => parseFloat(b.price) - parseFloat(a.price));
            }
            else{
                products.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));
            }
        }

        stopLoader();
        return products
    }

    let buildReportList = function(reportData){
        $('#results>table>tbody').html("");
        let i = 1;
        let total = 0;

        reportData.forEach(element => {
            $('#results>table>tbody').append(result.formatUnicorn({number:i, product: element.product, category:element.category, price: element.price, status: element.status}));
            i++;
            total += parseFloat(element.price);
        });
        $('#price-sum').html('$' + total);
    }

})(jQuery);


