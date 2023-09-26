package io.jenkins.plugins.designlibrary.Links;

import lib.JenkinsTagLib

def st=namespace("jelly:stapler")
def s = namespace("/lib/samples")

t=namespace(JenkinsTagLib.class)

def example(html) {
    tr {
        td {
            text(html)
        }
        td {
            raw(html)
        }
    }
}



s.sample(title:_("title")) {
    raw(_("blurb"))

    h2(_("define.title"))
    raw(_("blurb.define"))
    s.code(language:"java", file:"Links.java")

    h2(_("breadcrumb.title"))
    raw(_("blurb.breadcrumb"))


    h2(_("hyperlink.title"))
    raw(_("blurb.modelLink"))
    table(class:"jenkins-table") {
        example "<a href='.' class='model-link'>self</a>"
        example "<a href='..' class='model-link'>up</a>"
    }

    raw(_("blurb.modelLink.inside"))
    table(class:"jenkins-table") {
        example "<a href='.' class='model-link inside'>self</a>"
        example "<a href='..' class='model-link inside'>up</a>"
    }
}
