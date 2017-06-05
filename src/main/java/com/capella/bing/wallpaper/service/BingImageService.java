package com.capella.bing.wallpaper.service;

/**
 * @author Ramesh Rajendran
 */
public interface BingImageService {
    /**
     * Download today's image
     * @param downloadLocation
     */
    void todaysImage(String downloadLocation) throws Exception;
}
