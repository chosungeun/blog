
/*
index.js 첫 문장에 var main = {...} 라는 코드를 선언했습니다.
굳이 index 라는 변수 속성으로 function 을 추가한 이유는 뭘까요 ?

index.mustache 에서 a.js 가 추가되어 a.js 도 a.js 만의 init 과 sava function 이 있다면
어떻게 될까요 ?

블라우저 스코프는 공용공간으로 쓰이기 때문에 나중에 로딩된 js 의 init, sava 가
먼저 로딩된 js 의 function 을 덮어쓰게 됩니다.

여러 사람이 참여하는 프로젝트에서는 중복된 함수 이름은 자주 발생 할 수 있습니다.
function 이름을 확인하면서 만들 수 는 없습니다.

그러다 보니 이런 문제를 피하려고, index.js 만의 유효 범위를 만들어 사용합니다.

*/
var main = {
    init : function () {
        var _this = this;

        $('#btn-save').on('click', function(){
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function() {

        debugger;

        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type : 'post',
            url : '/api/v1/posts',
            datatype: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();