package com.example.library.test.snippet;

import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.Operation;
import org.springframework.restdocs.payload.AbstractFieldsSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//사용 X
public class CodeResponseFieldsSnippet extends AbstractFieldsSnippet {

    public CodeResponseFieldsSnippet(String type,
                                     PayloadSubsectionExtractor<?> subsectionExtractor,
                                     List<FieldDescriptor> descriptors,
                                     Map<String, Object> attributes,
                                     boolean ignoreUndocumentedFields) {
        super(type, descriptors, attributes, ignoreUndocumentedFields, subsectionExtractor);
    }

    public CodeResponseFieldsSnippet(String type,
//                                     PayloadSubsectionExtractor<?> subsectionExtractor,
                                     List<FieldDescriptor> descriptors,
                                     Map<String, Object> attributes,
                                     boolean ignoreUndocumentedFields) {
        super(type, descriptors, attributes, ignoreUndocumentedFields);
    }

    @Override
    protected MediaType getContentType(Operation operation) {
        return operation.getResponse().getHeaders().getContentType();
    }

    @Override
    protected byte[] getContent(Operation operation) {
        return operation.getResponse().getContent();
    }
}