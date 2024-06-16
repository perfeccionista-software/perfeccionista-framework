package io.perfeccionista.framework.exceptions.attachments;

import io.perfeccionista.framework.exceptions.attachments.processor.AttachmentProcessor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SetAttachmentProcessor {

    Class<? extends AttachmentProcessor>[] value();

}
