# This build file defines a target for the GAE Java Managed VM runtime.
# It is only visible to the Google App Engine product.

licenses(["notice"])  # Apache 2.0

package_group(
    name = "appengine",
    packages = [
        "//apphosting/...",
        "//java/com/google/appengine/...",
        "//java/com/google/apphosting/...",
        "//javatests/com/google/appengine/...",
        "//javatests/com/google/apphosting/...",
    ],
)

package(default_visibility = [
    ":appengine",
])

Fileset(
    name = "jetty_base",
    out = "jetty_base",
    entries = [
        FilesetEntry(
            files = glob(
                [
                    "**",
                ],
                exclude = [
                    "README.google",
                ],
            ),
        ),
    ],
)
