package com.capella.bing.wallpaper.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "loading",
        "previous",
        "next",
        "walle",
        "walls"
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tooltips {

    @JsonProperty("loading")
    public String loading;
    @JsonProperty("previous")
    public String previous;
    @JsonProperty("next")
    public String next;
    @JsonProperty("walle")
    public String walle;
    @JsonProperty("walls")
    public String walls;
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