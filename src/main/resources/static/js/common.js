function errorCheck(param)
{
    let obj = param.obj; //에러체크 객체가 된다.
    let length = param.length;
    let type=param.type;

    if(obj.value.length<length)
    {
        alert(length + "이상 입력하세요");
        obj.focus();
        return false;
    }

    return true;
}