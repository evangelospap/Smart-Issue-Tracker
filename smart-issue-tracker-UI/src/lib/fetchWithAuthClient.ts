"use client";
import { useAuth } from "@clerk/nextjs";

const BACKEND_API = process.env.NEXT_PUBLIC_BACKEND_API || "http://localhost:8081";

export function useFetchWithAuth() {
  const { getToken } = useAuth();

  return async function fetchWithAuthClient(path: string, options: RequestInit = {}) {
    const token = await getToken({ template: "Smart-Issue-Tracker" });
    if (!token) throw new Error("Not authenticated");

    const headers = {
      ...options.headers,
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    };

    const res = await fetch(`${BACKEND_API}${path}`, { ...options, headers });
    if (!res.ok) throw new Error(`Request failed: ${res.status} - ${await res.text()}`);

    return res.json();
  };
}
