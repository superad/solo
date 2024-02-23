<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSON格式化</title>
    <link rel="stylesheet" href="${staticServePath}/tools/json/css/base.css">
    <script src="${staticServePath}/tools/json/js/common.js" defer></script>
</head>
<body>
<div class="wrapper">
    <div class="main-wrap">
        <main>
            <div class="container">
                <div class="left-panel">
                    <button id="format-button">格式化</button>
                    <button id="escape-button">移除空格</button>
                    <button id="copy-button">复制</button>
                    <textarea id="input-json" placeholder="请输入 JSON 数据..."></textarea>
                </div>
                <div id="copySuccessMessage" class="copy-success-message">Copied!</div>
                <div class="right-panel">
                    <pre id="output-json"></pre>
                    <textarea id="output-json-value" class="hidden-textarea"></textarea>
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>
