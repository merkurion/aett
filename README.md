Java version 8

#How to
Run as usual Spring boot application.

<br />to Search:<br /><br />
(POST) /search/<br />
Body as JSON should match structure List<SearchCriteria> <br />
Key (Picture fields values): {id, croppedPicture, author, camera, tags, fullPicture}<br />
Value (any String without '%' cause operation can be 'MATCH')<br />
Operation (Search Operation enum): {GREATER_THAN,
                                        LESS_THAN,
                                        GREATER_THAN_EQUAL,
                                        LESS_THAN_EQUAL,
                                        NOT_EQUAL,
                                        EQUAL,
                                        MATCH,
                                        MATCH_START,
                                        MATCH_END,
                                        IN,
                                        NOT_IN}  <br /><br />
Example request:
[
    {
        "key": "tags",
        "value": "#photography",
        "operation": "MATCH"
    },
    {
        "key": "author",
        "value": "Sure-footed Assumption",
        "operation": "EQUAL"
    }
]
 <br /><br />
Example response:
[
    {
        "id": "be89995bc7886d5a7312",
        "author": "Sure-footed Assumption",
        "camera": "Nikon D4s",
        "tags": "#photography #today #beautifulday #whataview #view #beauty #nature ",
        "cropped_picture": "http://interview.agileengine.com/pictures/cropped/0002.jpg",
        "full_picture": "http://interview.agileengine.com/pictures/full_size/0002.jpg"
    }
]
