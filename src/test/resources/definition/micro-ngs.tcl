# This provides a tiny slice of the NGS API.

set NGS_YES *YES*
set NGS_NO  *NO*

# This is the actual implementation
proc ngs-match-top-state { id } {
    return "(state $id ^superstate nil)"
}

# This is not associated with the next proc because of the newline.

proc ngs-create-attribute { id attr value } {
    return "($id ^$attr $value)"
}

# The actual implementation of ngs-bind is fairly complicated - enough
# to warrant a multi-line comment.
proc ngs-bind { id angs } {
    return ""
}
