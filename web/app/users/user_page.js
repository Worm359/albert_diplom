/**
 * Created by Илья on 28.05.2016.
 */

    // 6 create an instance when the DOM is ready
    $('#jstree').jstree();
// 7 bind to events triggered on the tree
$('#jstree').on("changed.jstree", function (e, data) {
    console.log(data.selected);
});
// 8 interact with the tree - either way is OK
$('button').on('click', function () {
    $('#jstree').jstree(true).select_node('child_node_1');
    $('#jstree').jstree('select_node', 'child_node_1');
    $.jstree.reference('#jstree').select_node('child_node_1');
});

audiojs.events.ready(function()
{
    var as = audiojs.createAll();
});