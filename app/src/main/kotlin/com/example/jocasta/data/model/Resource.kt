package com.example.jocasta.data.model

/**
 * Resource interface to be implemented in named resource data classes - e.g., [Person].
 */
interface Resource

/**
 * A sealed class container consisting of all [Resource] call response statuses.
 */
sealed class ResourceResponse {

    /**
     * A [ResourceResponse] indicating an in-progress call awaiting response.
     */
    object Fetching : ResourceResponse()

    /**
     * A [ResourceResponse] indicating a successful call and response.
     *
     * @property resource a resulting [Resource].
     */
    data class Success(
        val resource: Resource
    ) : ResourceResponse()

    /**
     * A [ResourceResponse] indicating an unsuccessful call and response.
     */
    object Failure : ResourceResponse()
}

/**
 * Resource interface to be implemented in named resource set data classes - e.g. [PersonSet].
 */
interface ResourceSet

/**
 * A sealed class container consisting of all [ResourceSet] call response statuses.
 */
sealed class ResourceSetResponse {

    /**
     * A [ResourceSetResponse] indicating an in-progress call awaiting response.
     */
    object Fetching : ResourceSetResponse()

    /**
     * A [ResourceResponse] indicating a successful call and response.
     *
     * @property resourceSet a resulting [ResourceSet].
     */
    data class Success(
        val resourceSet: ResourceSet
    ) : ResourceSetResponse()

    /**
     * A [ResourceSetResponse] indicating an unsuccessful call and response.
     */
    object Failure : ResourceSetResponse()
}