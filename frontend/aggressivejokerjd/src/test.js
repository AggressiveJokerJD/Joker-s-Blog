const fs = require('fs');
const matter = require('gray-matter');
const { config } = require('process');
const str = fs.readFileSync('blog_need_to_do.md', 'utf8');
console.log(matter(str,{excerpt_separator: '<!-- more -->'}));
