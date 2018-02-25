app.controller('polisaSzukajCtrl', function ($scope, $http) {

    const scope = $scope;

    scope.statusy = [
        'ZATWIERDZONA',
        'ZAWIESZONA',
        'ROZWIAZANA'
    ];

    scope.model = {
        numerPolisy: null,
        statusPolisy: null
    }

    scope.polisy = [];

    scope.wyszukajPoNumerze = () => {
        $http({
            method: 'GET',
            url: `/polisa-jpa/api/polisa/szukaj/numer/${scope.model.numerPolisy}`,
            headers: { 'Content-Type': 'application/json ' }
        }).
            then((response) => {
                console.log(response);
                scope.polisy = response.data;
                //scope.polisy.push(response.data);
            }, (response) => {
                alert('Błąd podczas próby odczytu danych: ' + response.data);
            });
    }

    scope.wyszukajPoStatusie = () => {
        $http({
            method: 'GET',
            url: `/polisa-jpa/api/polisa/szukaj/status/${scope.model.statusPolisy}`,
            headers: { 'Content-Type': 'application/json ' }
        }).
            then((response) => {
                scope.polisy = response.data;
            }, (response) => {
                alert('Błąd podczas próby odczytu danych: ' + response.data);
            });
    }


});