package com.unknowndev.musically.Models;

import java.util.List;

public class TrendingSongModel {
    private String kind;
    private String etag;
    private List<Item> items;
    private String nextPageToken;
    private PageInfo pageInfo;

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public static class Item {
        private String kind;
        private String etag;
        private String id;
        private Snippet snippet;

        public String getKind() {
            return kind;
        }

        public String getEtag() {
            return etag;
        }

        public String getId() {
            return id;
        }

        public Snippet getSnippet() {
            return snippet;
        }
    }

    public static class Snippet {
        private String publishedAt;
        private String channelId;
        private String title;
        private String description;
        private Thumbnails thumbnails;
        private String channelTitle;
        private List<String> tags;
        private String categoryId;
        private String liveBroadcastContent;
        private Localized localized;
        private String defaultAudioLanguage;

        public String getPublishedAt() {
            return publishedAt;
        }

        public String getChannelId() {
            return channelId;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public Thumbnails getThumbnails() {
            return thumbnails;
        }

        public String getChannelTitle() {
            return channelTitle;
        }

        public List<String> getTags() {
            return tags;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public String getLiveBroadcastContent() {
            return liveBroadcastContent;
        }

        public Localized getLocalized() {
            return localized;
        }

        public String getDefaultAudioLanguage() {
            return defaultAudioLanguage;
        }
    }

    public static class Thumbnails {
        private Thumbnail defaultThumbnail;
        private Thumbnail medium;
        private Thumbnail high;
        private Thumbnail standard;
        private Thumbnail maxres;

        public Thumbnail getDefault() {
            return defaultThumbnail;
        }

        public Thumbnail getMedium() {
            return medium;
        }

        public Thumbnail getHigh() {
            return high;
        }

        public Thumbnail getStandard() {
            return standard;
        }

        public Thumbnail getMaxres() {
            return maxres;
        }

        public static class Thumbnail {
            private String url;
            private int width;
            private int height;

            public String getUrl() {
                return url;
            }

            public int getWidth() {
                return width;
            }

            public int getHeight() {
                return height;
            }
        }
    }

    public static class Localized {
        private String title;
        private String description;

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }

    public static class PageInfo {
        private int totalResults;
        private int resultsPerPage;

        public int getTotalResults() {
            return totalResults;
        }

        public int getResultsPerPage() {
            return resultsPerPage;
        }
    }
}
