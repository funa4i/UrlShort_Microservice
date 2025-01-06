package org.urlshort.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.urlshort.domain.data.UrlCreateRequest;
import org.urlshort.domain.data.UrlView;
import org.urlshort.domain.entities.Url;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UrlMapper {

    @Mapping(target = "fullUrl", source = "url")
    Url toUrl(UrlCreateRequest urlCreateRequest);

    @Mapping(target = "userid", expression = "java(url.getUserId())")
    UrlView toUrlView(Url url);

}
