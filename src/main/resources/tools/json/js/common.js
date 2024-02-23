document.getElementById('format-button').addEventListener('click', function() {
    var inputJson = document.getElementById('input-json').value.trim();
    if (inputJson) {
        try {
            var formattedJson = JSON.stringify(JSON.parse(inputJson), null, 2);
            document.getElementById('output-json').innerHTML = formatHtmlData(formattedJson);
            document.getElementById('output-json-value').value = formattedJson;
        } catch (error) {
            alert('Invalid JSON data: ' + error.message);
        }
    } else {
        alert('请输入 JSON 数据.');
    }
});


function formatHtmlData(jsonString){
    return jsonString.replace(/"(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|true|false|null|-?\d+(\.\d*)?(e[-+]?\d+)?/g, function(match) {
        var cls = 'json-number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'json-key';
            } else {
                cls = 'json-string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'json-boolean';
        } else if (/null/.test(match)) {
            cls = 'json-null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}

document.getElementById('escape-button').addEventListener('click', function() {
    var inputJson = document.getElementById('input-json').value.trim();
    if (inputJson) {
        try {
            var escapedJson = JSON.stringify(JSON.parse(inputJson));
            document.getElementById('output-json').innerHTML = formatHtmlData(escapedJson);
            document.getElementById('output-json-value').value = escapedJson;
        } catch (error) {
            alert('Invalid JSON data: ' + error.message);
        }
    } else {
        alert('Please enter JSON data.');
    }
});

// 添加点击事件处理程序
document.getElementById('copy-button').addEventListener('click', () => {
    // 获取按钮和提示框元素
    const copySuccessMessage = document.getElementById('copySuccessMessage');
    // 复制文本
    var textToCopy = document.getElementById('output-json-value').value;
    navigator.clipboard.writeText(textToCopy)
        .then(() => {
            // 复制成功时显示提示框
            copySuccessMessage.style.display = 'block';
            // 1.5秒后隐藏提示框
            setTimeout(() => {
                copySuccessMessage.style.display = 'none';
            }, 1500);
        })
        .catch(err => {
            console.error('Failed to copy:', err);
        });
});