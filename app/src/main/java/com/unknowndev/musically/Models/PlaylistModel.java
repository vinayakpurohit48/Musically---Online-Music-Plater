package com.unknowndev.musically.Models;

import java.io.Serializable;
import java.util.List;

// Root object for the entire response
public class PlaylistModel implements Serializable {
    private String kind;
    private String etag;
    private String nextPageToken;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private String regionCode;
    private PageInfo pageInfo;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public static class PageInfo implements Serializable{
        private int totalResults;
        private int resultsPerPage;

        public int getTotalResults() {
            return totalResults;
        }

        public int getResultsPerPage() {
            return resultsPerPage;
        }
    }

    // Item representing each video/playlist result
    public static class Item implements Serializable {
        private String kind;
        private String etag;
        private Id id;
        private Snippet snippet;

        public Snippet getSnippet() {
            return snippet;
        }

        public Id getId() {
            return id;
        }

        public static class Id implements Serializable {
            private String kind;
            private String playlistId;

            public String getPlaylistId() {
                return playlistId;
            }
        }

        public static class Snippet implements Serializable {
            private String publishedAt;
            private String channelId;
            private String title;
            private String description;
            private Thumbnails thumbnails;
            private String channelTitle;
            private String liveBroadcastContent;
            private String publishTime;

            public String getTitle() {
                return title;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public String getChannelId() {
                return channelId;
            }

            public String getDescription() {
                return description;
            }

            public String getChannelTitle() {
                return channelTitle;
            }

            public String getLiveBroadcastContent() {
                return liveBroadcastContent;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public Thumbnails getThumbnails() {
                return thumbnails;
            }

            public static class Thumbnails implements Serializable {
                private Thumbnail defaultThumbnail;
                private Thumbnail medium;
                private Thumbnail high;

                public Thumbnail getDefault() {
                    return defaultThumbnail;
                }

                public Thumbnail getMedium() {
                    return medium;
                }

                public Thumbnail getHigh() {
                    return high;
                }

                public static class Thumbnail implements Serializable {
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
        }
    }
}
