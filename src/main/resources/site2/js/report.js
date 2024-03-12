(function ($) {

    $('form button').on('click', function(e){
        e.preventDefault();
        if(fieldsOK()){
            window.location.href = "report.html";
        }
    })

    let fieldsOK = function(){
        if(datesEmpty()){
            displayAlert("Dates can't be empty.");
            return false;
        }
        if(!datesCoherent()){
            displayAlert("From date should be minor than To date.");
            return false;
        }

        if(!selectedReportOk()){
            displayAlert("The selected report is not implemented yet. Try a different report");
            return false;
        }

        if(!selectedReportContentOk()){
            displayAlert("Only the full report is available.");
            return false;
        }
        return true;
    }

    let displayAlert = function toogleAlert(messageToDisplay){
        let message = alertMessage.formatUnicorn({message:messageToDisplay});
        $('#message').append(message);
        return false;
    }

    let datesEmpty = function(){
        return $('#fromDate').val() === '' || $('#toDate').val() === '';
    }

    let datesCoherent = function(){
        return $('#fromDate').val() < $('#toDate').val();
    }

    let selectedReportOk = function(){
        return $('#selectedReport').children("option:selected").val() == "prices";
    }

    let selectedReportContentOk = function(){
        let selectedType = $('input[name="reportType"]:checked').val();
        return selectedType === 'full';
    }

})(jQuery);
