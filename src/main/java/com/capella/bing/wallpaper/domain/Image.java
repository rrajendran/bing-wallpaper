package com.capella.bing.wallpaper.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "startdate",
        "fullstartdate",
        "enddate",
        "url",
        "urlbase",
        "copyright",
        "copyrightlink",
        "quiz",
        "wp",
        "hsh",
        "drk",
        "top",
        "bot",
        "hs"
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @JsonProperty("startdate")
    public String startdate;
    @JsonProperty("fullstartdate")
    public String fullstartdate;
    @JsonProperty("enddate")
    public String enddate;
    @JsonProperty("url")
    public String url;
    @JsonProperty("urlbase")
    public String urlbase;
    @JsonProperty("copyright")
    public String copyright;
    @JsonProperty("copyrightlink")
    public String copyrightlink;
    @JsonProperty("quiz")
    public String quiz;
    @JsonProperty("wp")
    public Boolean wp;
    @JsonProperty("hsh")
    public String hsh;
    @JsonProperty("drk")
    public Integer drk;
    @JsonProperty("top")
    public Integer top;
    @JsonProperty("bot")
    public Integer bot;
    @JsonProperty("hs")
    public List<Object> hs = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}