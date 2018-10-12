<?xml version="1.0" encoding="UTF-8"?> 
<kml xmlns="http://www.opengis.net/kml/2.2"> 
    <Document>
        <#list locations as o>
            <Placemark>
                <name>${o.name}</name>
                <description>${o.address}</description>
                <Point>
                    <coordinates>${o.coords}</coordinates>
                </Point>
            </Placemark>
        </#list>

        <Placemark>
            <name>test</name>
            <description>test Desc</description>
                <LineString>
                    <coordinates>
                        ${route}
                    </coordinates>
                </LineString>
            <Style>
                <LineStyle>
                    <color>#ff0000ff</color>
                    <width>5</width>
                </LineStyle>
            </Style>
        </Placemark>
    </Document>
</kml>