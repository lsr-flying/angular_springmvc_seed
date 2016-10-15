/**
 * Created by LINSHIRUI447 on 2016-09-04.
 */
appModule.controller("authorizationMgtCtrl",["$scope","HelloWorldService","HelloFactory","helloConstant","$filter",
    function($scope,HelloWorldService,HelloFactory,helloConstant,$filter){
    $scope.userName = HelloWorldService.greeting("Tim");
    $scope.helloFactory = HelloFactory.getPrivateString();
    $scope.helloConstant =$filter("helloFilter")(helloConstant.version);
    $scope.salary = 25000;
}]);

