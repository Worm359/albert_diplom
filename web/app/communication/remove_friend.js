/**
 * Created by Илья on 25.05.2016.
 */
var req;
var friend_ID;
var friend_table;
function removeFriend(friendID, friendTable)
{
    var url = "http://localhost:8080/albert_diplom_war/app/controller?action=removeUserFromFriends&sub="+friendID;
    if(confirm("Уверен что хочешь удалить пользователя "+friendID+"?"))
    {
        var i=friendTable.parentNode.parentNode.rowIndex;
        /*i = document.getElementById*/
        document.getElementById("usersTable").deleteRow(i);
        req = new XMLHttpRequest();
        req.open("GET", url, true);
        req.onreadystatechange = callback;
        friend_ID = friendID;
        friend_table = friendTable;
        req.send(null);
    }
}

function callback() {
    if (req.readyState == 4) {
        if (req.status == 200) {
            isSuccessfull(req.responseXML);
        }
    }
}

function isSuccessfull(responseXML)
{
    if (responseXML == null)
    {
        return false;
    }
    else
    {
        alert("successfully deleted!");
        return true;
    }
}




