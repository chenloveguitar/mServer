$(function () {
            	Canvas2();
        });
        function Canvas2() {
            var a = function () { return Math.round(Math.random() * 100) };
            var b = function () { return Math.round(Math.random() * 500) };
            var c = function () { return Math.round(Math.random() * 1000) };
            var lineChartData = {
                labels: ["5.1", "5.2", "5.3", "5.4", "5.5","5.6","5.7","5.8","5.9","5.10"],
                datasets: [
                    {
                        fillColor: "rgba(246,209,207,0)",
                        strokeColor: "rgba(108,235,205,1)",
                        pointColor: "rgba(255,255,255,1)",
                        pointStrokeColor: "rgba(108,235,205,1)",
                        pointHighlightFill: "rgba(108,235,205,1)",
                        data: [50,a(),a(),a(),a(),a(),a(),a(),a(),a()]
                    },
                    {
                    		fillColor: "rgba(246,209,207,0)",
                        strokeColor: "rgba(187,69,67,1)",
                        pointColor: "rgba(255,255,255,1)",
                        pointStrokeColor: "rgba(187,69,67,1)",
                        pointHighlightFill: "rgba(187,69,67,1)",
                        data: [500,b(),b(),b(),b(),b(),b(),b(),b(),b()]
                    },
                    {
                    		fillColor: "rgba(246,209,207,0)",
                        strokeColor: "rgba(16,197,228,1)",
                        pointColor: "rgba(255,255,255,1)",
                        pointStrokeColor: "rgba(16,197,228,1)",
                        pointHighlightFill: "rgba(16,197,228,1)",
                        data: [1000,c(),c(),c(),c(),c(),c(),c(),c(),c()]	
                    }
                ]
            }
            var ctx = document.getElementById("Canvas2").getContext("2d");
            window.myLine = new Chart(ctx).Line(lineChartData, {
                bezierCurve:false,
            });
            
            $('.li-clicked').click(function(){
            	$(this).addClass('a-clicked').siblings().removeClass('a-clicked');
            })
}



	
	