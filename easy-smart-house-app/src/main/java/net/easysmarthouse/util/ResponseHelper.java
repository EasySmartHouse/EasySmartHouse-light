package net.easysmarthouse.util;

import net.easysmarthouse.shared.domain.Space;

import javax.servlet.http.HttpServletRequest;

public class ResponseHelper {

    private ResponseHelper() {
    }

    public static void setFullImageUrl(final Space space, String pathToReplace,
                                       final HttpServletRequest request) {
        final String imagesUrl = request.getRequestURL().toString()
                .replace(pathToReplace, "/images");
        space.setImage(imagesUrl + "/" + space.getImage());
    }

}
