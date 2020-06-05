<content:title>
    <fmt:message key="upload.epub.file" />
</content:title>

<content:section cssId="storyBookCreateFromEPubPage">
    <h4><content:gettitle /></h4>
    <div class="card-panel" style="padding: 3em 0;">
        <form:form modelAttribute="storyBook" enctype="multipart/form-data">
            <tag:formErrors modelAttribute="storyBook" />
            
            <div class="col s6 offset-s3" style="padding: 3em; background: #F4F4F4; border: 2px dashed #CCC;">
                
                <p class="center grey-text">
                    Drag & drop your ePUB file here, or select a file.
                </p>
                <div class="file-field input-field col s8 offset-s2 center">
                    <div class="btn deep-purple">
                        <span><fmt:message key='file' /></span>
                        <%--<form:input path="bytes" type="file" />--%>
                        <input name="bytes" id="bytes" type="file" />
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" />
                    </div>
                    <script>
                        $(function() {
                            $('#bytes').on('change', function() {
                                console.info('#bytes on change');
                                
                                $(this).parents('form').submit();
                            });
                        });
                    </script>
                </div>
                
            </div>
            <div style="clear: both;"></div>
        </form:form>
    </div>
</content:section>

<content:aside>
    <h5 class="center"><fmt:message key="resources" /></h5>
    <div class="card-panel deep-purple lighten-5">
        <ul>
            <li>
                <a href="https://digitallibrary.io" target="_blank">Global Digital Library</a>
            </li>
            <li>
                <a href="https://reader.letsreadasia.org" target="_blank">Let's Read</a>
            </li>
            <li>
                <a href="https://storyweaver.org.in/stories" target="_blank">StoryWeaver</a>
            </li>
        </ul>
    </div>
</content:aside>
