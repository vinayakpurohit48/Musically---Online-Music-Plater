package com.unknowndev.musically.Models;

import java.util.List;

public class PlaylistSongModel {

    private String kind;
    private String etag;
    private String nextPageToken;
    private List<PlaylistItem> items;
    private PageInfo pageInfo;

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

    public List<PlaylistItem> getItems() {
        return items;
    }

    public void setItems(List<PlaylistItem> items) {
        this.items = items;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public static class PlaylistItem {
        private String kind;
        private String etag;
        private String id;
        private Snippet snippet;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        public static class Snippet {
            private String publishedAt;
            private String channelId;
            private String title;
            private String description;
            private Thumbnails thumbnails;
            private String channelTitle;
            private String playlistId;
            private int position;
            private ResourceId resourceId;
            private String videoOwnerChannelTitle;
            private String videoOwnerChannelId;

            // Getters and Setters

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Thumbnails getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(Thumbnails thumbnails) {
                this.thumbnails = thumbnails;
            }

            public String getChannelTitle() {
                return channelTitle;
            }

            public void setChannelTitle(String channelTitle) {
                this.channelTitle = channelTitle;
            }

            public String getPlaylistId() {
                return playlistId;
            }

            public void setPlaylistId(String playlistId) {
                this.playlistId = playlistId;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public ResourceId getResourceId() {
                return resourceId;
            }

            public void setResourceId(ResourceId resourceId) {
                this.resourceId = resourceId;
            }

            public String getVideoOwnerChannelTitle() {
                return videoOwnerChannelTitle;
            }

            public void setVideoOwnerChannelTitle(String videoOwnerChannelTitle) {
                this.videoOwnerChannelTitle = videoOwnerChannelTitle;
            }

            public String getVideoOwnerChannelId() {
                return videoOwnerChannelId;
            }

            public void setVideoOwnerChannelId(String videoOwnerChannelId) {
                this.videoOwnerChannelId = videoOwnerChannelId;
            }

            public static class Thumbnails {
                private Thumbnail defaultThumbnail;
                private Thumbnail medium;
                private Thumbnail high;
                private Thumbnail standard;
                private Thumbnail maxres;

                // Getters and Setters

                public Thumbnail getDefaultThumbnail() {
                    return defaultThumbnail;
                }

                public void setDefaultThumbnail(Thumbnail defaultThumbnail) {
                    this.defaultThumbnail = defaultThumbnail;
                }

                public Thumbnail getMedium() {
                    return medium;
                }

                public void setMedium(Thumbnail medium) {
                    this.medium = medium;
                }

                public Thumbnail getHigh() {
                    return high;
                }

                public void setHigh(Thumbnail high) {
                    this.high = high;
                }

                public Thumbnail getStandard() {
                    return standard;
                }

                public void setStandard(Thumbnail standard) {
                    this.standard = standard;
                }

                public Thumbnail getMaxres() {
                    return maxres;
                }

                public void setMaxres(Thumbnail maxres) {
                    this.maxres = maxres;
                }

                public static class Thumbnail {
                    private String url;
                    private int width;
                    private int height;

                    // Getters and Setters

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }
                }
            }

            public static class ResourceId {
                private String kind;
                private String videoId;

                // Getters and Setters

                public String getKind() {
                    return kind;
                }

                public void setKind(String kind) {
                    this.kind = kind;
                }

                public String getVideoId() {
                    return videoId;
                }

                public void setVideoId(String videoId) {
                    this.videoId = videoId;
                }
            }
        }
    }

    public static class PageInfo {
        private int totalResults;
        private int resultsPerPage;

        // Getters and Setters

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public int getResultsPerPage() {
            return resultsPerPage;
        }

        public void setResultsPerPage(int resultsPerPage) {
            this.resultsPerPage = resultsPerPage;
        }
    }
}
