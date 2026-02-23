## What makes a Service RESTful?

Adding hypermedia

Spring HATEOAS, a Spring project aimed at helping you write hypermedia-driven outputs.

This library give us the constructs that define a RESTful service then render it in an acceptable format for the client consumption.

To make your controller more RESTful.

One of the Spring HATEOAS's core type is Link. It includes a URI and a rel(relation)

HAL is a lightweight mediatype that allows encoding not only data but also hypermedia controls, alerting consumers to other parts of the API to which they can navigate.

By adding the links to all the employees. The "collection" is listed underneath the "\_embedded" section. This is how HAL represents collections. Each individual memeber of the collection has their information as well related links.
