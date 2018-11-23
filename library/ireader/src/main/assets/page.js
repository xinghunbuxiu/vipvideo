String.prototype.chomp = function() {
  return this.replace(/(\n|\r)+$/, "")
}
String.prototype.format = function() {
  for (var t = this, e = 0; arguments.length > e; e++) {
    var i = RegExp("\\{" + e + "\\}", "gi");
    t = t.replace(i, arguments[e])
  }
  return t
}
var n = {
  SimSun: "SimSun, 宋体, 'Heiti SC'",
  "Microsoft Yahei": "'Microsoft Yahei', 微软雅黑, STHeiti, Hei, 'Heiti SC', 黑体",
  SimKai: "'Kaiti SC', STKaiti, 楷体",
  SimFang: "STFangsong, FangSong, 仿宋",
  Symbol: "DK-SYMBOL, 'Segoe UI', Georgia, Sans-Serif",
  "DK-SYMBOL": "DK-SYMBOL, 'Segoe UI', Georgia, Sans-Serif",
  "DK-SERIF": "DK-SERIF, 'Palatino Linotype', Serif",
  "DK-SERIF-BOLD": "DK-SERIF-BOLD, 'Palatino Linotype', Serif",
  "DK-SERIF-BOLD-ITALIC": "DK-SERIF-BOLD-ITALIC, 'Palatino Linotype', Serif",
  "DK-SERIF-ITALIC": "DK-SERIF-ITALIC, 'Palatino Linotype', Serif",
  "DK-CODE": "DK-CODE, 'Lucida Console', Monospace"
};

var a = [48, 96, 128, 192, 256, 384, 1024],
  drawPage = function(t, e) {
    createElement(t);
    init(e);
    draw();

    $(".book_page_wrapper").css("width", e.page_size[0]);
    $(".book_page_wrapper").css("height", e.page_size[1]);
  }

createElement = function(id) {
  this.book_note_id = "book_page_" + id;
  this.root_note = $(".j-page-container");
  var wrapper_note = "<div class='book_page_wrapper'></div>";
  var book_note = "<div class='book_page'></div>";
  root_note.append(wrapper_note);
  this.el = $(".book_page_wrapper");
  el.append(book_note);
  $(".book_page").attr('id', this.book_note_id);
}

init = function(e) {
  this.id = "#" + this.book_note_id,
    this.page = e,
    this.width = e.page_size[0],
    this.height = e.page_size[1],
    $(this.id).css("width", this.width),
    $(this.id).css("height", this.height),
    "fullscreen" == this.page.type ? $(this.id).parent().addClass("fullscreen") : this.page.nopadding && $(this.id).addClass("nopadding"),
    this.bglayer = this.create_layer("background"),
    this.pathlayer = this.create_layer("path"),
    this.imagelayer = this.create_layer("image"),
    this.marklayer = this.create_layer("mark"),
    this.selectionlayer = this.create_layer("selection"),
    this.selectionlayer.css("background", "rgba(0,0,0,0)"),
    this.textlayer = this.create_layer("text"),
    this.textbuffer = [],
    this.galleries = [],
    this.is_selection_started = !1,
    this.selectedtext = [],
    this.selectedlines = [],
    this.marks = [];

}
create_layer = function(t) {
  var e = $("<div></div>");
  var book_note = $(this.id);
  return e.addClass(t),
    e.css("width", this.width).css("height", this.height).css("position", "absolute"),
    book_note.append(e),
    e
}

draw = function() {
  var t = $.Deferred();
  t.done(function() {
        this.flush_textbuffer(this.textbuffer, this.textlayer),
          delete this.textbuffer,
          this.textbuffer = []
      }
      .bind(this)),
    t.done(this.draw_galleries.bind(this));
  for (var e = this.page.items, i = 0; e.length > i; i++)
    this.draw_item(e[i]);
  t.resolve()
}
draw_item = function(t) {
  if ("word" == t.type)
    this.textbuffer.push(t);
  else if ("image" == t.type)
    this.load_image(t).done(function(e) {
        if (t.interactive) {
          var i = $("<div class='image_outer_border'></div>");
          i.css("top", parseInt(e.css("top"), 10) - 3).css("left", parseInt(e.css("left"), 10) - 3).css("width", t.x1 - t.x0).css("height", t.y1 - t.y0).css("position", "absolute"),
            e.css("top", 0).css("left", 0).css("width", t.width - 6).css("height", t.height - 6).css("z-index", c);
          var s = $("<div class='image_border'></div>");
          s.css("position", "absolute").css("width", i.width() - 6).css("height", i.height() - 6),
            s.append(e),
            i.append(s),
            this.imagelayer.append(i),
            e.click(function() {
                this.load_image(t, t.orig_src, !0).done(function(e) {
                    var i = t.resized_width,
                      s = t.resized_height;
                    e.attr("style", "");
                    var n = t.width / t.tmat.m11,
                      o = t.height / t.tmat.m11;
                    $("img", e).attr("style", "").width(n).height(o);
                    var r = $(".u-showbox"),
                      a = $(".u-showbox .cnt");
                    if (a.css("left", Math.ceil((r.width() - i) / 2)), a.css("top", Math.ceil((r.height() - s) / 2)), a.empty(), a.append(e), t.maintitle) {
                      var _ = $("<div class='txt'></div>");
                      _.text(t.maintitle),
                        e.append(_),
                        _.css("width", t.resized_width - 100)
                    }
                    a.css("position", "absolute"),
                      r.show(),
                      this.trigger("interactive_image_shown", {
                        width: i,
                        height: s
                      })
                  }
                  .bind(this))
              }
              .bind(this))
        } else
          this.imagelayer.append(e), "true" == e.attr("is_link") && (e.css("cursor", "pointer"), e.css("z-index", h), e.click(b.bind(this)))
      }
      .bind(this));
  else if ("bg_image" == t.type)
    this.load_image(t).done(function(e) {
        var i = $("img", e).attr("src");
        this.bglayer.css("width", t.bound.width).css("height", t.bound.height).css("top", t.bound.y).css("left", t.bound.x).css("background", "url('" + i + "')").css("background-repeat", t.repeat).css("background-size", "contain").css("background-position", "{0}px {1}px".format(t.x - t.bound.x, t.y - t.bound.y)),
          t.size && this.bglayer.css("background-size", t.size)
      }
      .bind(this));
  else if ("path" == t.type)
    this.draw_path(t, this.pathlayer);
  else if ("footnote" == t.type)
    this.draw_footnote(t, this.imagelayer);
  else if ("gallery" == t.type) {
    t.current_cell = 0;
    var e = this.create_layer("gallery");
    t.layer = e,
      t.tag = "gallery" + (this.galleries.length + ""),
      this.galleries.push(t)
  } else
    "audio" == t.type || "video" == t.type
};
draw_path = function(t, e) {
  if (t.desc && (e || (e = this.pathlayer), e.canvas || (e.canvas = Raphael(e[0], this.width, this.height)), t["fill-color"] || t["stroke-color"])) {
    if (t["fill-color"]) {
      var i = /rgba\(([0-9]+,){3}([0-9]+)\)/,
        s = t["fill-color"].match(i)[2];
      if (0 === Number(s)) {
        if (!t["stroke-color"])
          return;
        if (s = t["stroke-color"].match(i)[2], 0 === Number(s))
          return
      }
    }
    var n = e.canvas.path(t.desc);
    t["fill-color"] && n.attr("fill", t["fill-color"]),
      t["stroke-color"] ? n.attr("stroke", t["stroke-color"]) : n.attr("stroke-width", 0)
  }
}
gallery_next = function(t) {
  this.gallery_goto(t, t.current_cell + 1)
}
gallery_prev = function(t) {
  this.gallery_goto(t, t.current_cell - 1)
}
gallery_goto = function(t, e) {
  e >= 0 && t.cells.length > e && (t.current_cell = e, this.draw_gallery(t))
}
draw_gallery = function(t) {
  var e = t.layer;
  e.css("width", "0px"),
    e.css("height", "0px"),
    e.html("");
  for (var i = [], s = t.cells[t.current_cell], n = s.items, o = this, r = function(i, s) {
      if (e.append(i), s.is_target) {
        var n = $(".gallery_prev", i);
        0 === n.length && (n = $("<button class='gallery_prev'>Prev</div>"), n.click(function() {
            this.gallery_prev(t)
          }
          .bind(o)), i.append(n));
        var r = $(".gallery_next", i);
        0 === r.length && (r = $("<button class='gallery_next'>Next</div>"), r.click(function() {
            this.gallery_next(t)
          }
          .bind(o)), i.append(r));
        var a = t.current_cell;
        a > 0 ? n.show() : n.hide(),
          t.cells.length - 1 > a ? r.show() : r.hide();
        var _ = t.bound;
        if (_) {
          var h = $(".gallery_dots", i);
          if (0 === h.length) {
            h = $("<ul class='gallery_dots'></ul>"),
              on_dot_click = function() {
                var e = $(this).attr("to");
                o.gallery_goto(t, parseInt(e, 10))
              };
            for (var c = 0; t.cells.length > c; c++) {
              var d = $("<li class='gallery_dot'>dot</li>");
              d.attr("to", c),
                c == t.current_cell && d.addClass("active"),
                d.click(on_dot_click),
                h.append(d)
            }
          }
          h.css("top", _.y + _.height).css("left", _.x).css("width", _.width).css("height", 10),
            e.append(h)
        }
      }
    }, a = [], _ = 0; n.length > _; _++) {
    var h = n[_];
    "word" == h.type ? i.push(h) : "image" == h.type ? a.push(h) : "path" == h.type ? this.draw_path(h, e) : "footnote" == h.type && this.draw_footnote(h, e)
  }
  if (a) {
    var c = a[0];
    for (_ = 1; a.length > _; _++) {
      var d = a[_].width * a[_].height;
      d > c.width * c.height && (c = a[_])
    }
    c.is_target = !0
  }
  $.each(a, function(t, e) {
      this.load_image(e).done(r)
    }
    .bind(this));
  var l = $("text", this.textlayer).filter(function() {
    return $(this).attr("tag") === t.tag
  });
  l.remove(),
    this.flush_textbuffer(i, this.textlayer, t.tag)
}
draw_galleries = function() {
  for (var t = this.galleries, e = 0; t.length > e; e++)
    this.draw_gallery(t[e])
}
draw_footnote = function(t, e) {
  var i = this;
  e || (e = this.imagelayer),
    this.load_image(t).done(function(s) {
      e.append(s),
        s.css("z-index", 100),
        s.attr("note-data", t.text),
        "richtext" != t.ftype && s.bind("mouseenter", function() {
          var e = $("#footer-note");
          e.find("p").text($(this).attr("note-data")),
            i.showPopupAtPoint(e, {
              x: t.x1 - 5,
              y: t.y1
            }, 20),
            function(t, e) {
              var i = t.data("__nicescroll");
              i && i.remove();
              var s = {
                cursorminheight: 10,
                cursorcolor: "#9f662d",
                cursorwidth: "5px",
                cursorborder: "none",
                cursorborderradius: "5px",
                autohidemode: !1,
                bouncescroll: !1,
                horizrailenabled: !1,
                smoothscroll: !1
              };
              t.niceScroll(e, s)
            }
            (e.find(".note-wrap"), e.find(".note-wrap p")),
            e.on("click", function(t) {
              t.stopPropagation()
            }),
            $("body").one("click", function() {
              e.hide()
            })
        })
    })
};

load_image = function(t, e, i) {
    function s(e, i) {
      var s = $('<div class="image-clip" style="position:absolute; overflow:hidden"></div>');
      (void 0 === t.width || void 0 === t.height) && (t.width = t.x1 - t.x0, t.height = t.y1 - t.y0),
      void 0 !== t.cx0 ? (s.css("left", Math.round(t.x0)), s.css("top", Math.round(t.y0)), s.css("width", Math.round((t.cx1 - t.cx0) * t.tmat.m11)), s.css("height", Math.round((t.cy1 - t.cy0) * t.tmat.m22))) : (s.css("left", Math.round(t.x0)), s.css("top", Math.round(t.y0)), s.css("width", Math.round(t.width)), s.css("height", Math.round(t.height)));
      var n = $("<img></img>");
      n.attr("src", i);
      var o = 0,
        r = 0;
      if (void 0 !== t.cx0 && t.tmat && (o = -t.cx0 * t.tmat.m11, r = -t.cy0 * t.tmat.m22), n.css("position", "absolute").css("left", Math.round(o)).css("top", Math.round(r)).css("width", Math.round(t.width)).css("height", Math.round(t.height)), s.append(n), void 0 !== t.link_to && e.page.links) {
        var a = e.page.links[t.link_to];
        s.attr("is_link", "true"),
          "internal" == a.type ? (s.attr("to_page", a.page), s.attr("to_chapter", a.chapter)) : "external" == a.type && (a.url, s.attr("to_url", a.url))
      }
      return s
    }
    var n = $.Deferred();
    e || (e = t.src);
    var o = this.is_url(e);
    if (o || r[e]) {
      var _;
      if (o) {
        _ = e;
        for (var h = t.width, c = 0; a.length > c && h > a[c]; c++);
        c == a.length && (c = a.length - 1),
          h = a[c],
          i || (_ += "?thumb=" + h + "x" + "&scale=auto")
      } else
        _ = r[e];
      var d = s(this, _);
      setTimeout(function() {
        n.resolve(d, t)
      }, 0)
    } else
      $.getBSON("/image/" + e, function(i) {
          r[e] = i.url;
          var o = s(this, r[e]);
          n.resolve(o, t)
        }
        .bind(this));
    return n.promise()
  },
  is_url = function(t) {
    return /^https?\:\/\//.test(t)
  }
flush_textbuffer = function(t, e, i) {
  var s,
    r = document.implementation.hasFeature("http://www.w3.org/TR/SVG11/feature#BasicStructure", "1.1"),
    a = "http://www.w3.org/2000/svg",
    _ = document.createDocumentFragment();
  r ? (s = $("svg", e), 0 === s.length ? (s = document.createElementNS(a, "svg"), s.setAttribute("xmlns", a), s.setAttribute("version", 1.1), s.setAttribute("width", e.width()), s.setAttribute("height", e.height())) : s = s[0]) : s = document.createElement("div");
  for (var h = 0; t.length > h; h++) {
    var c = t[h],
      d = c.bound;
    if (d || (d = {
        x: c.x,
        y: c.y - c["font-size"],
        width: c["font-size"],
        height: 1.2 * c["font-size"]
      }, c.bound = d), "DK-SERIF" == c["font-family"] && (c["font-family"] = "italic" == c["font-style"] ? "bold" == c["font-weight"] || "bolder" == c["font-weight"] ? "DK-SERIF-BOLD-ITALIC" : "DK-SERIF-ITALIC" : "bold" == c["font-weight"] || "bolder" == c["font-weight"] ? "DK-SERIF-BOLD" : "DK-SERIF", delete c["font-weight"], delete c["font-style"]), n[c["font-family"]] && (c["font-family"] = n[c["font-family"]]), void 0 !== c.link_to && this.page.links) {
      var l = this.page.links[c.link_to],
        u = $("<div></div>");
      u.css("position", "absolute").css("background-color", "#FFF").css("opacity", "0").css("cursor", "pointer").css("z-index", "100").css("width", d.width).css("height", d.height).css("left", d.x).css("top", d.y),
        e.append(u),
        "internal" == l.type ? (u.attr("to_page", l.page), u.attr("to_chapter", l.chapter)) : "external" == l.type && (l.url, u.attr("to_url", l.url)),
        u.click(b.bind(this))
    }
    if (r) {
      var f = document.createElementNS(a, "text");
      f.appendChild(document.createTextNode(c.char));
      for (var p in c)
        f.setAttribute(p, c[p]);
      f.removeAttribute("bound"),
        f.removeAttribute("char"),
        f.removeAttribute("type"),
        f.removeAttribute("pos"),
        i && f.setAttribute("tag", i),
        _.appendChild(f)
    } else {
      var g = /([0-9]+),([0-9]+),([0-9]+),([0-9]+)/,
        m = g.exec(c.fill).slice(1),
        v = "rgb({0}, {1}, {2})".format(m[0], m[1], m[2]),
        y = document.createElement("span");
      y.style["font-family"] = c["font-family"],
        y.style["font-size"] = "" + c["font-size"] + "px",
        y.style.color = v,
        y.style.left = "" + c.x + "px",
        y.style.position = "absolute",
        c["font-style"] && (y.style["font-style"] = c["font-style"]),
        c["font-weight"] && (y.style["font-weight"] = c["font-weight"]),
        y.appendChild(document.createTextNode(c.char));
      var w;
      o[c["font-family"]] && o[c["font-family"]][c["font-size"]] ? w = o[c["font-family"]][c["font-size"]] : (e[0].appendChild(y), w = y.offsetHeight, o[c["font-family"]] || (o[c["font-family"]] = {}), " " != c.char && (o[c["font-family"]][c["font-size"]] = w), e[0].removeChild(y)),
        y.style.top = "" + (c.y - w + .6 * c["font-size"]) + "px",
        _.appendChild(y)
    }
  }
  s.appendChild(_),
    e[0].appendChild(s)
};

function e(t, e) {
  if (!t && e)
    return -1;
  if (!e && t)
    return 1;
  if (!t && !e)
    return 0;
  for (var i = 0; 3 > i; i++)
    if (t[i] != e[i])
      return t[i] - e[i];
  return 0
}

function i(t) {
  return [t[2], t[3], t[4], t[1]]
}
