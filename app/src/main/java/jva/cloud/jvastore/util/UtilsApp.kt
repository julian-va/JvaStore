package jva.cloud.jvastore.util

import jva.cloud.jvastore.util.ConstantApp.DEFAULT_IMAGE

object UtilsApp {
    fun reprocessImageFromApi(images: List<String>): String {
        var finalImageStr: String = DEFAULT_IMAGE
        images.firstOrNull()?.let { image ->
            finalImageStr = image.replace(oldValue = "[\"", newValue = "")
                .replace(oldValue = "\"]", newValue = "")
                .replace(oldValue = "\"", newValue = "")
        }
        return finalImageStr
    }
}