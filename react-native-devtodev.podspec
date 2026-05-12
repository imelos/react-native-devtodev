require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))
source_url = package["repository"]["url"].sub(/^git\+/, "")

Pod::Spec.new do |s|
  s.name         = package["name"]
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.author       = package["author"]
  s.source       = { :git => source_url, :tag => "v#{s.version}" }
  s.platform     = :ios, "9.0"
  s.source_files = "ios/**/*.{h,m}"

  s.dependency "React-Core"
  s.dependency "DTDAnalytics", "~> 2.0.0"
end
