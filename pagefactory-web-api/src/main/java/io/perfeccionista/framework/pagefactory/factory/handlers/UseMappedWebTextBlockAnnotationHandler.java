package io.perfeccionista.framework.pagefactory.factory.handlers;

import static io.perfeccionista.framework.utils.CastUtils.isSubtypeOf;

@Deprecated
public class UseMappedWebTextBlockAnnotationHandler {

    private UseMappedWebTextBlockAnnotationHandler() {
    }

//    public static @NotNull WebItemFrame<DefaultWebTextBlock> createWebTextListFrame(@NotNull WebTextList webTextList,
//                                                                                    @NotNull Method elementMethod,
//                                                                                    @NotNull WebPageFactory webPageFactory,
//                                                                                    @NotNull WebPageFactoryPreferences configuration) {
//        Class<? extends WebBlock> webMappedBlockClass = configuration.getWebMappedBlock(webTextList.getClass());
//
//        Optional<UseMappedWebTextBlock> optionalClassAnnotation = findFirstAnnotationInHierarchy(UseMappedWebTextBlock.class,
//                WebChildElement.class, webTextList.getClass());
//        if (optionalClassAnnotation.isPresent()) {
//            webMappedBlockClass = optionalClassAnnotation.get().value();
//        }
//
//        Optional<UseMappedWebTextBlock> optionalMethodAnnotation = findAnnotation(elementMethod, UseMappedWebTextBlock.class);
//        if (optionalMethodAnnotation.isPresent()) {
//            webMappedBlockClass = optionalMethodAnnotation.get().value();
//        }
//
//        DefaultWebTextBlock webTextListBlock = null;
//
//        if (Objects.nonNull(webMappedBlockClass)) {
//            if (!isSubtypeOf(webMappedBlockClass, DefaultWebTextBlock.class)) {
//                throw MappedBlockIncorrectType
//                        .exception(MAPPED_BLOCK_IMPLEMENTATION_INCORRECT_TYPE.getMessage(DefaultWebTextBlock.class.getCanonicalName()));
//            }
//            webTextListBlock = (DefaultWebTextBlock) webPageFactory
//                    .createMappedWebBlock(webTextList, webMappedBlockClass);
//        } else {
//            webTextListBlock = webPageFactory.createMappedWebBlock(webTextList, DefaultWebTextBlock.class);
//        }
//
//        return new WebItemFrame<>(webTextListBlock);
//    }

}
