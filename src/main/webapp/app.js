const app = angular.module("app", ['ui.router']);

app.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider
        .when('/', '/polisa')
        .otherwise('/polisa');

    $stateProvider
        .state("polisa", {
            url: '/polisa',
            templateUrl: "polisa.html"
        })
        .state("szukaj", {
            url: '/szukaj',
            templateUrl: "szukajPolise.html"
        });
});