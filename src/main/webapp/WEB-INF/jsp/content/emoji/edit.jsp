<content:title>
    <fmt:message key="edit.emoji" />
</content:title>

<content:section cssId="emojiEditPage">
    <h4><content:gettitle /></h4>
    <div class="card-panel">
        <form:form modelAttribute="emoji">
            <tag:formErrors modelAttribute="emoji" />

            <div class="row">
                <form:hidden path="language" value="${emoji.language}" />
                <form:hidden path="revisionNumber" value="${emoji.revisionNumber}" />
                
                <div class="input-field col s12">
                    <form:label path="glyph" cssErrorClass="error"><fmt:message key='glyph' /></form:label>
                    <form:input path="glyph" cssErrorClass="error" placeholder="🦋" />
                </div>
                
                <div class="input-field col s12">
                    <form:label path="unicodeVersion" cssErrorClass="error"><fmt:message key='unicode.version' /></form:label>
                    <form:input path="unicodeVersion" cssErrorClass="error" placeholder="9.0" />
                </div>
                
                <div class="input-field col s12">
                    <form:label path="unicodeEmojiVersion" cssErrorClass="error"><fmt:message key='unicode.emoji.version' /></form:label>
                    <form:input path="unicodeEmojiVersion" cssErrorClass="error" placeholder="3.0" />
                </div>
            </div>

            <button id="submitButton" class="btn waves-effect waves-light" type="submit">
                <fmt:message key="edit" /> <i class="material-icons right">send</i>
            </button>
            <a href="<spring:url value='/content/emoji/delete/${emoji.id}' />" class="waves-effect waves-red red-text btn-flat right"><fmt:message key="delete" /></a>
        </form:form>
    </div>
</content:section>

<content:aside>
    <h5 class="center"><fmt:message key="preview" /></h5>
    
    <div class="previewContainer valignwrapper">
        <img src="<spring:url value='/static/img/device-pixel-c.png' />" alt="<fmt:message key="preview" />" />
        <div id="previewContentContainer">
            <div id="previewContent" class="previewContentGrapheme">

            </div>
        </div>
    </div>
    <script>
        $(function() {
            initializePreview();
            
            $('#glyph').on("change", function() {
                console.debug('#glyph on change');
                initializePreview();
            });
            
            function initializePreview() {
                console.debug('initializePreview');
                var value = $('#glyph').val();
                if ((value != undefined) && (value != "")) {
                    $('#previewContent').html(value);
                }
            };
        });
    </script>
</content:aside>
