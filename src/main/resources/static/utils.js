function refresh(){
    $.ajax({
        type: 'get',
        url:'http://localhost:8080/jokeRefresh',
        success:function(data){
            $('#joke').html(data);
        }
    })
}